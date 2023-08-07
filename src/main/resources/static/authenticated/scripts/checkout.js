const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],

    data() {

        return {
       
          selectedProductCombos: [],
          selectedProducts: [],
          setCreateTicketDto: [],
          savePurchaseError: "",
          requestTicketsDto: [],
          readyToPay: false,
          purchaseProductCombos: [],
          purchaseProductItems: [],
          purchaseTickets: [],
          purchaseId: "",
          username: "",
          cardNumber: "",
          cardUpto: "",
          cardCvv: "",
          totalPrice: "",
          purchaseDto: [],



            
        }
    },
    created() {


this.getStorageTickets();

     
        

    },



    

    computed: {

       
          
          



    },
    methods: {

      getStorageTickets(){
        this.setCreateTicketDto = JSON.parse(sessionStorage.getItem('cineverse-Tickets'));
        this.getStorageProductCombos();
      },

      getStorageProductCombos(){
        this.selectedProductCombos = JSON.parse(sessionStorage.getItem('selectedCombos'));
        this.getStorageProducts();
      },

      getStorageProducts(){
        this.selectedProducts = JSON.parse(sessionStorage.getItem('selectedProducts'))
        this.requestTickets();
        
      },





      requestTickets(){
        axios.post("/api/current/ticket_request", this.setCreateTicketDto)
        .then(response => {
          this.requestTicketsDto = response.data;
        })
      },
  

      savePurchase(){
        this.buyTickets()
      },

      buyTickets(){
        console.log(this.setCreateTicketDto);
        axios.post('/api/current/create_ticket', this.setCreateTicketDto)
        .then(response => {
          
          this.purchaseId = response.data;
          sessionStorage.setItem('purchaseId', this.purchaseId);
    
          this.buyProductCombos();
        })
        .catch(Error => {
          console.log(Error);
          this.savePurchaseError = Error.response.data;
        })
    
     
        
          
         
      },

      buyProductCombos(){
     
        axios.post("/api/current/add_product_combos", this.selectedProductCombos, {
            params: {
                purchaseId: this.purchaseId
            }
        }).then(response => {
          this.buyProducts();
      
        })
        .catch(Error => {
          this.savePurchaseError = Error.message;
        })
        
      },

      buyProducts(){
        axios.post("/api/current/purchase/add_purchase_item", this.selectedProducts, {
            params: {
                purchaseId: this.purchaseId
            }
        }).then(response => this.getPurchaseDto())

        .catch(Error => {
          this.savePurchaseError = Error.message;
        })
      },




      getPurchaseDto(){

        axios.get(`/api/current/purchase/${this.purchaseId}`)
        .then(response => {
          
          this.purchaseDto = response.data;
          console.log(this.purchaseDto);
          this.purchaseTickets = this.purchaseDto.tickets;
          this.purchaseProductCombos = this.purchaseDto.productCombos;
          this.purchaseProductItems = this.purchaseDto.purchaseItems;
          this.totalPrice = this.purchaseDto.purchase_price;
          this.readyToPay = true;
          
      })

    },




      pay(){


        axios.post("http://localhost:9090/procesarcompra")
        .then(response => {
          console.log('Respuesta:', response.data);
          this.purchaseStatusToComplete();
        })
        .catch(error => {
       
          console.error('Error:', error);
        });
      },

      purchaseStatusToComplete(){

        axios.patch(`/api/current/purchase/${this.purchaseId}`)
        .then(response => console.log(response.data))
        .catch(error => {
       
          console.error('Error:', error);
        });
      }






        
          



    },

    beforeUnmount() {
   
  
      // Enviar la petición utilizando Axios
      axios.patch('/api/current/purchase/cancel', this.purchaseId , {
        timeout: 1000, // Establecer un tiempo de espera corto (1 segundo) para evitar bloqueos
      }).then(() => {
        console.log('Compra cancelada correctamente.');
      }).catch((error) => {
        console.error('Error al cancelar la compra:', error.message);
      });
    },


    



}).mount('#app')
