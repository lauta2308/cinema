

const { createApp } = Vue



createApp({

    data() {

        return {
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
        let paymentDate = {
          userName: this.userName,

          cardNumber: this.cardNumber,
      
         uptoDate: this.uptoDate,
      
          cvv: this.cardCvv
        }
        axios.post(`https://localhost:9090/procesar-compra`, paymentDate)
        .then(response => console.log(response.data));
      }




        
          



    },


    



}).mount('#app')
