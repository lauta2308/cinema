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
            this.loadShowTimes("MOVIE_2D")
        } else if(this.showTimeTyoe === "3d") {
            this.loadShowTimes("MOVIE_3D")
        } 
        
        else {
            this.loadShowTimes("MOVIE_IMAX")
        }
    },

loadShowTimes(movieType){

    axios.get("/api/showtimes", {
        params: {
            movieType: movieType
        }
    }).then(response => this.showTimes = response.data)
},

    saveShowId(show){
        sessionStorage.setItem('showId', show.id);
        sessionStorage.setItem('roomId', show.cinemaRoom.id);
    
        window.location.href = "../authenticated/buy-tickets.html"
    }

      



    },


    



}).mount('#app')
