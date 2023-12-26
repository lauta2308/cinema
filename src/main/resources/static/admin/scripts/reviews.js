const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            reviews: [],
            errorMessage: "",
 

        
              
  
              
          }
      },
      created() {
  

        this.getReviews();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     

  
  
  
      },
      methods: {
  



        
    


        
        getReviews(){
            axios.get("/api/admin/get_reviews")
            .then(response => {
                this.reviews = response.data;
               
                this.reviews.sort(function(a, b) {
                    return a.id.localeCompare(b.id);
                });



            })
            .catch(Error => this.errorMessage = Error.response.data );
        },

        changeReviewStatus(review, status){

            let reviewDto = review;

            reviewDto.reviewStatus = status;

            console.log(status)
            axios.patch("/api/admin/change_review_status", reviewDto)
            .then(response => this.getReviews())

            .catch(Error => this.errorMessage = Error.response.data );




        },




        resetErrorMessage(){
            this.errorMessage = "";
          },
  
      
  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  