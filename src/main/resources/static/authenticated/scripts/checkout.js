const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],

    data() {

        return {
       
          selectedProductCombos: [],
          selectedProducts: [],
          selectedCreateTicketDto: [],
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

this.getPurchaseId();

     
        

    },



    

    computed: {

       
          
          



    },
    methods: {




          getPurchaseId(){
            this.purchaseId = sessionStorage.getItem('purchaseId');
            this.getPurchaseDto();
          },

        getPurchaseDto(){

          axios.get(`/api/current/purchase/${this.purchaseId}`)
          .then(response => {
            
            this.purchaseDto = response.data;
            this.totalPrice = this.purchaseDto.purchase_price;
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
