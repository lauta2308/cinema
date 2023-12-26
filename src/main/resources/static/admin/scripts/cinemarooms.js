const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            cinemaRooms: [],
            enableEditCinemaRoom: false,
            newRoomName: "",
            newRoomCapacity: "",
            newRoomType: "",
            newRoomStatus: "",
            errorMessage: ""
         
              
  
              
          }
      },
      created() {
  

        this.getCinemaRooms();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         
            
            
  
  
  
      },
      methods: {
  
    
      getCinemaRooms(){

        axios.get("/api/admin/cinema_rooms")
        .then(response => {
            
          console.log(response.data)
            
            this.cinemaRooms = response.data
            this.cinemaRooms.sort(function(a, b) {
                return a.roomName.localeCompare(b.roomName);
            });
            
        })

        

      },

      saveNewRoom(){
        console.log(typeof(this.newRoomCapacity) == 'number')
        if(this.newRoomName.length == 0){
            this.errorMessage = "Add room name";
        } else if(this.newRoomCapacity.length == 0){
            this.errorMessage = "Add room capacity";
        } else if(typeof(this.newRoomCapacity)  != 'number'){
            this.errorMessage = "Room capacity should be a number"
        } else if(this.newRoomCapacity <= 0){
            this.errorMessage = "Room capacity should be higher than 0"
        } else if(this.newRoomType.length == 0){
            this.errorMessage = "Room type should not be empty"
        } else {
            let newRoom = [this.newProductName, this.newRoomCapacity, this.newRoomType]
            axios.post("/api/admin/cinema_room", {
               roomName: this.newRoomName,

                capacity: this.newRoomCapacity,
            
                roomType: this.newRoomType
            }).then(response => this.getCinemaRooms())
            .catch(Error => this.errorMessage = Error.response.data);
        }

      
      },


      
      resetErrorMessage(){
        this.errorMessage = "";
      },


  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  