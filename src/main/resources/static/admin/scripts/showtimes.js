const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            shows: [],
            errorMessage: "",
        


        
              
  
              
          }
      },
      created() {
  

        this.getShows();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         textAreaLimit(){
            return 500 - this.newMovieDescription.length 
         }
            
            
  
  
  
      },
      methods: {
  

        getShows(){
            axios.get("/api/admin/get_shows")
            .then(response => {
                this.shows = response.data;
               
        



            })
            .catch(Error => this.errorMessage = Error.response.data );
        },

  
      
  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  