const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';




createApp({
  mixins: [menuBehavior, userLogged],

    data() {

        return {
     
         
           

            
        }
    },
    created() {

    
     

       

        

    },



    

    computed: {




    },
    methods: {

      saveStoragePurchase(purchase){
        sessionStorage.setItem("purchaseToChange", JSON.stringify(purchase.id));
        sessionStorage.setItem("changeTickets", true);
        sessionStorage.setItem("showtimeType", JSON.stringify(purchase.tickets[0].show.movie.movieType));

        window.location.href = "../web/showtimes.html"

      }


        
     

    



    },


    



}).mount('#app')
