

const { createApp } = Vue



createApp({

    data() {

        return {
     
            isMenuOpen: false,
           
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

    

        
        getUserLogged(){
                 axios.get("/api/authenticated_user")
                .then(response => {
                    this.clientRole = response.data.clientRol,
                    this.clientName = response.data.name
                 } )
                 .then(response => localStorage.setItem('cineverseLogin', false));
        
                },

               
        

    
      
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
          },

         

     

    



    },


    



}).mount('#app')
