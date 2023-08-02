

const { createApp } = Vue



createApp({

    data() {

        return {
          isAuthenticated: "",
          isMenuOpen: false,
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
      checkUserAuth(){
        axios.get("/api/current/isAuthenticated")
        .then(response => this.isAuthenticated = response.data);
      },

      logout(){
        axios.post("/api/logout")
        .then(response => window.location.href="./index.html");
    },

        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
          },

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
          // Aquí puedes trabajar con la respuesta recibida desde el back-end
          console.log('Respuesta:', response.data);
        })
        .catch(error => {
          // Manejo de errores
          console.error('Error:', error);
        });
        // let paymentDate = {
          
        // }
        // axios.post(`https://localhost:9090/procesar-compra`, paymentDate)
        // .then(response => console.log(response.data));
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
