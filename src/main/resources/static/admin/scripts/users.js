const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            users: [],
            enableEditCinemaRoom: false,
    
         
              
  
              
          }
      },
      created() {
  

        this.getUsers();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         
            
            
  
  
  
      },
      methods: {
  
    
      getUsers(){

        axios.get("/api/admin/get_users")
        .then(response => {
            
          console.log(response.data)
            
            this.users = response.data
            this.users.sort(function(a, b) {
                return a.lastName.localeCompare(b.lastName);
            });
            
        })

        

      },

      banUser(user){

   
        axios.patch("/api/admin/ban_user", {

          "id": user.id,
          "email": user.email

        })
        .then(this.getUsers())
      },


      unBanUser(user){
        axios.patch("/api/admin/unban_user", {

          "id": user.id,
          "email": user.email

        })
        .then(this.getUsers())
      },
         


      
      resetErrorMessage(){
        this.errorMessage = "";
      },


  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  