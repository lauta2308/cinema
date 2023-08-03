const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";



createApp({
  mixins: [menuBehavior],

    data() {

        return {
     
            isMenuOpen: false,
            clientLogged: false,
            clientRole: "",
            clientName: "",

            
        }
    },
    created() {

    
        this.getUserLogged();

       

        

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

         

     

    



    },


    



}).mount('#app')
