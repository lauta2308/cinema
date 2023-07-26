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
        console.log(document);

        

    },



    

    computed: {




    },
    methods: {

    

        
             getUserLogged(){
                 axios.get("/api/authenticated_user")
                .then(response => {
                    this.clientRole = response.data.clientRol,
                    this.clientName = response.data.name,
                    this.clientLogged = true;
                 } )
                 .then(response => localStorage.setItem('cineverseLogin', false));
        
                },

               
        

    
      
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
          },

         
         loadMovies() {
         
            axios.get(`/api/get_on_schedule_movies`).then((response) => {
                this.movies = response.data,
                this.slides = this.movies.length;
               console.log(this.movies)
                
         })
            
        },

         showMovie(movie){

            try {
                // Verificar si el objeto movie es válido para JSON antes de guardarlo
                const movieJson = JSON.stringify(movie);
                // Si no hay errores en la serialización, guardar el objeto en el localStorage
                localStorage.setItem('movie', movieJson);
                // Redirigir a la página de detalles de la película
                window.location.href = "./web/movie.html";
              } catch (error) {
                console.error("Error al serializar el objeto movie:", error);
              }

        },

        checkMovieInLocal(){
            const movieJson = localStorage.getItem('movie');
            console.log("Valor obtenido del localStorage:", movieJson);
            if (movieJson && typeof movieJson === 'string') {
              this.movieSelected = JSON.parse(movieJson);
              
            }

         
        },


         getMovieShows(){

            axios.get('/api/movie_shows', {
                params: {
                  movieId: this.movieSelected.id
                }
              }).then(response => {
                
                this.movieShows = response.data;
                console.log(this.movieShows);
               
             } )


        },

        formatStartDate(date){
            console.log(date);
            const [datePart, timePart] = date.split('T');

            const dateFormated = `${datePart} ${timePart}`;
            return dateFormated;
        },

        formatEndTime(date){
            console.log(date);
            const [datePart, timePart] = date.split('T');

            const dateFormated = `${timePart}`;
            return dateFormated;
        },

     

    



    },


    



}).mount('#app')


