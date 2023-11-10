const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            movies: [],
            errorMessage: "",
        
              
  
              
          }
      },
      created() {
  

        this.getMovies();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         
            
            
  
  
  
      },
      methods: {
  

        
        getMovies(){
            axios.get("/api/admin/get_movies")
            .then(response => {
                this.movies = response.data;
               
                this.movies.sort(function(a, b) {
                    return a.name.localeCompare(b.name);
                });



            })
            .catch(Error => this.errorMessage = Error.response.data );
        },

        reviewsLength(movie){

            return movie.reviews.length;


        },
  
      
  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  