const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],
    data() {

        return {
              
            movie_ticket: "",
            show_selected: "",

            
        }
    },
    created() {

      
        

     
        

    },



    

    computed: {
   


          
          
          



    },
    methods: {

    

      



    },


    



}).mount('#app')