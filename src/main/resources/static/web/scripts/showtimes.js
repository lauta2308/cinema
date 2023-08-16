const { createApp } = Vue
import menuBehavior from "./menu.js";
import userLogged from './userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],
    data() {

        return {
              
            showTimeType: "",
            showTimes: [],

            
        }
    },
    created() {

      this.checkShowTimesInStorage();
        

     
        

    },



    

    computed: {
   


          
          
          



    },
    methods: {

    checkShowTimesInStorage(){
        const showTimeType = sessionStorage.getItem('showtimeType');

        this.showTimeType = JSON.parse(showTimeType);

        if(this.showTimeType === "2d"){
            this.load2dShowTimes()
        } else if(this.showTimeTyoe === "3d") {
            this.load3dShowTimes();
        } 
        
        else {
            this.loadImaxShowTimes()
        }
    },

    load2dShowTimes(){

        axios.get("/api/2d_shows")
        .then(response => this.showTimes = response.data)
    },

    load3dShowTimes(){
        axios.get("/api/3d_shows")
        .then(response => this.showTimes = response.data)
    },

    loadImaxShowTimes(){
        axios.get("/api/imax_shows")
        .then(response => this.showTimes = response.data)
    },

    saveShowId(show){
        sessionStorage.setItem('showId', show.id);
        sessionStorage.setItem('roomId', show.cinemaRoom.id);
    
        window.location.href = "../authenticated/buy-tickets.html"
    }

      



    },


    



}).mount('#app')
