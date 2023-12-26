const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            cinemaRooms: [],
            enableEditCinemaRoom: false,
            roomId: "",
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

      editRoom(room){

        this.roomId = room.id;

        this.enableEditCinemaRoom = true;

        this.newRoomName = room.roomName,

        this.newRoomCapacity = room.capacity,

        this.newRoomStatus = room.roomStatus,

        this.newRoomType = room.roomType
    
        
      },

      cancelEditRoom(){
        this.enableEditCinemaRoom = false;
        this.newRoomName = "",
        this.newRoomCapacity = "",
        this.newRoomType = "",
        this.newRoomStatus = ""

      },

      saveEditRoom(){

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
        } else if(this.newRoomStatus.length == 0){
            this.errorMessage = "Room status should not be empty"
        } else {
            axios.patch("/api/admin/cinema_room", {
                roomId: this.roomId,
                roomName: this.newRoomName,
                capacity: this.newRoomCapacity,
                roomType: this.newRoomType,
                roomStatus: this.newRoomStatus
            }).then(response => this.getCinemaRooms())
            .catch(Error => this.errorMessage = Error.response.data);
        }
         



      

     
      },


      
      resetErrorMessage(){
        this.errorMessage = "";
      },


  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  