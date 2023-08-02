const { createApp } = Vue



createApp({

    data() {

        return {
     
            isMenuOpen: false,
            movies: [],
            slides: 7,
            clientLogged: false,
            clientRole: "",
            clientName: "",
            movieSelected: {},
            movieShows: []

            
        }
    },
    created() {

        this.loadMovies();
        this.getUserLogged();
        this.checkMovieInLocal();
        this.getMovieShows();
       

        

    },



    

    computed: {




    },
    methods: {

            logout(){
                axios.post("/api/logout")
                .then(response => window.location.href="./index.html");
            },

        
             getUserLogged(){
                 axios.get("/api/authenticated_user")
                .then(response => {
                    this.clientRole = response.data.clientRol,
                    this.clientName = response.data.name,
                    this.clientLogged = true;
                 } )
                 .then(response => sessionStorage.setItem('cineverseLogin', false));
        
                },

               
        

    
      
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
          },

         
         loadMovies() {
         
            axios.get(`/api/get_on_schedule_movies`).then((response) => {
                this.movies = response.data,
                this.slides = this.movies.length;
      
                
         })
            
        },

         showMovie(movie){

            try {
                // Verificar si el objeto movie es válido para JSON antes de guardarlo
                const movieJson = JSON.stringify(movie);
                // Si no hay errores en la serialización, guardar el objeto en el localStorage
                sessionStorage.setItem('movie', movieJson);
                // Redirigir a la página de detalles de la película
                window.location.href = "./web/movie.html";
              } catch (error) {
                    console.log(error);
              }

        },

        checkMovieInLocal(){
            const movieJson = sessionStorage.getItem('movie');

            if (movieJson && typeof movieJson === 'string') {
              this.movieSelected = JSON.parse(movieJson);
              
            }

         
        },


         getMovieShows(){

            console.log(this.movieSelected.id);

            axios.get('/api/movie_shows', {

                params: {
                    movieId: this.movieSelected.id
                }
               
                 
                
              }).then(response => {
                
                this.movieShows = response.data;
     
               
             } )


        },

        formatStartDate(date){
        
            const [datePart, timePart] = date.split('T');

            const dateFormated = `${datePart} ${timePart}`;
            return dateFormated;
        },

        formatEndTime(date){

            const [datePart, timePart] = date.split('T');

            const dateFormated = `${timePart}`;
            return dateFormated;
        },

        saveShowId(show){
            sessionStorage.setItem('showId', show.id);
            sessionStorage.setItem('roomId', show.cinemaRoom.id);
        
            window.location.href = "../authenticated/buy-tickets.html"
        }

     

    



    },


    



}).mount('#app')


