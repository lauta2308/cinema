
const userLogged = {
    data() {
      return {

        userAuthenticated: false,
        clientRole: "",
        clientLogged: false,
        clientName: "",
        clientPurchases: [],
        clientReviews: []

      };
    },
    created() {

            this.isAuthenticated()
            
        
       

    },
    methods: {

      isAuthenticated(){

        axios.get("/api/isAuthenticated")
        .then(response => {
            
            this.userAuthenticated = response.data;
            if(this.userAuthenticated){
                this.getUserLogged();
            }

        })
      } , 

      getUserLogged(){

             
                    axios.get("/api/authenticated_user")
                    .then(response => {
                        this.clientRole = response.data.clientRol;
                        this.clientLogged = true;
                        this.clientName = response.data.name;
                        this.clientPurchases = response.data.purchases;
                        this.clientReviews = response.data.reviews;
                      
          })
            
            

          .catch(error => {
            console.error("Error en la petición:", error.message);
          });
    
        
                },

             
    },
  };
  
  export default userLogged; 