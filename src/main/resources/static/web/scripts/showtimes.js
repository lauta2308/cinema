const { createApp } = Vue
import menuBehavior from "./menu.js";
import userLogged from './userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],
    data() {

        return {
              
            changeTicketsEnabled: false,
            showTimeType: "",
            showTimes: [],

            
        }
    },
    created() {

      this.checkChangeTicketsStorage();

        

     
        

    },



    

    computed: {
   


          
          
          



    },
    methods: {

    checkChangeTicketsStorage(){
        const changeTicketsEnabled = sessionStorage.getItem("changeTickets");
        this.changeTicketsEnabled = JSON.parse(changeTicketsEnabled);

        this.checkShowTimesInStorage();


    },

  

    checkShowTimesInStorage(){
        const showTimeType = sessionStorage.getItem('showtimeType');

        this.showTimeType = JSON.parse(showTimeType);

        if(this.showTimeType === "MOVIE_2D"){
            this.loadShowTimes("MOVIE_2D")
        } else if(this.showTimeTyoe === "MOVIE_3D") {
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
        console.log(show.cinemaRoom.id);
        sessionStorage.setItem('showId', show.id);
        sessionStorage.setItem('roomId', show.cinemaRoom.id);
    
        window.location.href = "../authenticated/buy-tickets.html"
    }

      



    },


    



}).mount('#app')
