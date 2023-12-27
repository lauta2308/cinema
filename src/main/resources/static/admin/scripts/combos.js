const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            combos: [],
         

              
  
              
          }
      },
      created() {
  

        this.getCombos();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         
            
            
  
  
  
      },
      methods: {
  
    
      getCombos(){

        axios.get("/api/current/get_product_combos")
        .then(response => {
            
          console.log(response.data)
            
            this.combos = response.data
     
        })

        

      },

  
          

      
      resetErrorMessage(){
        this.errorMessage = "";
      },


  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  