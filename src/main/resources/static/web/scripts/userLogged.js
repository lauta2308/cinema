



const userLogged = {

  
    data() {
      return {

        userAuthenticated: false,
        clientRole: "",
        clientLogged: false,
        clientName: "",
        clientReviews: [],
        clientCompletedPurchases: [],
        clientUsedPurchases: [],
        userMovieReview: [],

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
                        this.clientReviews = response.data.reviews;
                        this.getMovieIdInLocal();
                        this.getCompletedPurchases();
                        this.getUsedPurchases();
                      
          })
            
            

          .catch(error => {
            console.error("Error en la petición:", error.message);
          });
    
        
                },

        
                getMovieIdInLocal(){
            
                  console.log("from user logged");
                  const movieJson = sessionStorage.getItem('movie');
      
                  if (movieJson && typeof movieJson === 'string') {
                    this.existUserReview(JSON.parse(movieJson));
          
                    
                  }
      
               
              },


      
        existUserReview(movieIdParam){

                  axios.get(`/api/current/get_user_review_by_movie`, {
                    params: {
                      movieId: movieIdParam
                    }
                  })
                  .then(response => this.userMovieReview.push(response.data),
            
                    
                    
                  )
                  .catch(Error => console.log(Error));
        
                },


        getCompletedPurchases(){
          axios.get("/api/current/get_completed_purchases")
          .then(response => {
            
            console.log(response.data)
            this.clientCompletedPurchases = response.data
          
          
          })
          .catch(Error => console.log(Error.response.data));
        },

        getUsedPurchases(){
          axios.get("/api/current/get_used_purchases")
          .then(response => this.clientUsedPurchases = response.data)
          .catch(Error => console.log(Error.response.data));
        },

             
    },
  };
  
  export default userLogged; 