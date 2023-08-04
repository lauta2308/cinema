const { createApp } = Vue;
import menuBehavior from './menu.js';
import userLogged from './userLogged.js';




createApp({

    mixins: [menuBehavior, userLogged],
    data() {

        return {
            slides: 7,
            movies: [],
            movieSelected: {},
            movieShows: []

            
        }
    },
    created() {

        this.loadMovies();
 
     


        

    },


    

    computed: {




    },
    methods: {


                      
        

    
    
         
         loadMovies() {
         
            axios.get(`/api/get_on_schedule_movies`).then((response) => {
                this.movies = response.data,
                this.slides = this.movies.length;
      
                
         })
            
        },

         showMovie(movie){

            try {
                // Verificar si el objeto movie es válido para JSON antes de guardarlo
                const movieJson = JSON.stringify(movie.id);
                // Si no hay errores en la serialización, guardar el objeto en el localStorage
                sessionStorage.setItem('movie', movieJson);
                // Redirigir a la página de detalles de la película
                window.location.href = "./web/movie.html";
              } catch (error) {
                    console.log(error);
              }

        },

    


        

     

    



    },


    



}).mount('#app')


