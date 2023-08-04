const { createApp } = Vue;
import menuBehavior from './menu.js';
import userLogged from './userLogged.js';




createApp({

    mixins: [menuBehavior, userLogged],
    data() {
      return {

        movieSelected: "",
        movieShows: [],

      };
    },
    created() {

        this.checkMovieInLocal();
        

    },
    methods: {

        checkMovieInLocal(){
            
            const movieJson = sessionStorage.getItem('movie');

            if (movieJson && typeof movieJson === 'string') {
              this.loadMovie(JSON.parse(movieJson));
    
              
            }

         
        },

        loadMovie(movieId){

            axios.get(`/api/movies/${movieId}`)
            .then(response => {
                
                
                this.movieSelected = response.data;
                this.getMovieShows();
                
            })
        },



        getMovieShows(){
          
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
  }
}).mount('#app')