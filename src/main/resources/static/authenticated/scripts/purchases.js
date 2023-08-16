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


      // save purchase id to change tickets

      saveStoragePurchaseId(purchaseId){
        sessionStorage.setItem("purchaseToChange", JSON.stringify(purchaseId));
      }

        
     

    



    },


    



}).mount('#app')
