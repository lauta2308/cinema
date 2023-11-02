

const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],
    data() {

        return {
              
            showId: "",
            roomSeats: [],
            availableSeatsFromBackend: [],
            selectedSeats: [],
            seatsPerRow: 10, // Número de asientos por fila
            seatsPerPage: 5, // Número de asientos por página
            currentPage: 1, // Página actual
            ageError: "",
            changeTicketsProcess: false,
            purchaseIdToChange: "",
            ticketsToChange: [],
            ticketsToChangeAmount: "",
      
            

            
        }
    },
    created() {

      
        
        this.getShowAndRoomId();
        this.checkTicketsToChange();
     
        

    },



    

    computed: {
   

        rows() {
            const rows = [];
            for (let i = 0; i < this.roomSeats.length; i += this.seatsPerRow) {
              const row = this.roomSeats.slice(i, i + this.seatsPerRow);
              rows.push(row);
            }
            return rows;
        },

         // Creamos una propiedad computada para los asientos que se mostrarán en la página actual
    displayedSeats() {
        const startIdx = (this.currentPage - 1) * this.seatsPerPage;
        const endIdx = startIdx + this.seatsPerPage;
        return this.selectedSeats.slice(startIdx, endIdx);
      },
  
      // Calculamos el total de páginas necesarias para mostrar todos los asientos seleccionados
      totalPages() {
        return Math.ceil(this.selectedSeats.length / this.seatsPerPage);
      }
          
          
          



    },
    methods: {

      checkTicketsToChange(){

         
          if(JSON.parse(sessionStorage.getItem("changeTickets") === "true")){
             
              this.changeTicketsProcess = true;
              this.getTicketsToChange(JSON.parse(sessionStorage.getItem('purchaseToChange')));
          }
      },

      getTicketsToChange(purchaseId){
        console.log("purchase is " + purchaseId);


        axios.get(`/api/current/purchase/${purchaseId}`
         
        ).then(response => {
          this.purchaseIdToChange = response.data.id;
          console.log(response.data.tickets);
          this.ticketsToChange = response.data.tickets;
          this.ticketsToChangeAmount = this.ticketsToChange.length;
          console.log(response.data);
       
       
         
       } )
        

      },

      setCustomerAge(index){
          this.selectedSeats[index].customerAge = this.ticketsToChange[index].customerAge;
          return this.ticketsToChange[index].customerAge;
      },

    

      resetAgeError(){
        this.ageError = "";
      },

        

        getShowAndRoomId(){

            this.showId = sessionStorage.getItem('showId');
            this.roomId = sessionStorage.getItem('roomId');

            this.getSeatsAvailable();
            this.getRoomSeats();
        },

        getRoomSeats(){
         
            axios.get('/api/current/room_seats', {
                params: {
                  cinemaRoomId: this.roomId
                }
              }).then(response => {
                this.roomSeats = response.data;
              
               
             } )

        },

               
          getSeatsAvailable(){
            axios.get('/api/current/seats_available', {
                params: {
                  showId: this.showId
                }
              }).then(response => {
                this.availableSeatsFromBackend = response.data;
             
             
               
             } )


          },
        
        toggleSeatSelection(seat) {
            if (this.isSeatAvailable(seat)) {
              if (!this.selectedSeats.includes(seat)) {
                
                this.selectedSeats.push(seat);
              }
            }
          },
          isSeatAvailable(seat) {
            return this.availableSeatsFromBackend.some(availableSeat => availableSeat.id === seat.id);
          },

          removeSeat(index) {
            this.selectedSeats.splice(index, 1);
          },

    
      
    
          // Función para ir a la página anterior
    prevPage() {
        if (this.currentPage > 1) {
          this.currentPage--;
        }
      },
  
      // Función para ir a la página siguiente
      nextPage() {
        if (this.currentPage < this.totalPages) {
          this.currentPage++;
        }
      },

      
      

     

      createTickets(){

        if(this.selectedSeats.some(element => !element.customerAge)){
          this.ageError = "Select the age for all tickets to continue";
        }

        else {
          this.selectedSeats.forEach(element => {
            element.showId = this.showId;
            
          });

          this.ageError = "";
          console.log(this.selectedSeats);
          let createTicketDto = this.selectedSeats;
        
  
  
          console.log(createTicketDto);
          sessionStorage.setItem('cineverse-Tickets', JSON.stringify(createTicketDto));
  
          
           window.location.href = "./products.html"
          
        }
       

      },

      patchTickets(){

     
        this.selectedSeats.forEach((element, index) => {
          element.ticketId = this.ticketsToChange[index].id;
          element.customerAge = this.ticketsToChange[index].customerAge;
          element.showId = this.showId;
          
        });


        console.log(this.selectedSeats);

        axios.patch("/api/current/change_tickets", this.selectedSeats, {
          params: {
            purchaseId: this.purchaseIdToChange
          }
        }).then(response => {

          console.log(response.data);
        })

      }

    


    



    },


    



}).mount('#app')
