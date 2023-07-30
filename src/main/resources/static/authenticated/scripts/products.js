

const { createApp } = Vue



createApp({

    data() {

        return {
            isMenuOpen: false,
          createTicketDto: [],
          productCombos: [],
          products: [],
          selectedProductCombos: [],
          selectedProducts: [],
          totalPrice: 0,
          purchaseId: ""


            
        }
    },
    created() {

  this.getProductCombos();
  this.getProducts();
  this.getStorageTickets();
     
        

    },



    

    computed: {

       
          
          



    },
    methods: {

        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
          },

          getProductCombos(){
                axios.get("/api/current/get_product_combos")
                .then(response => {
                    this.productCombos = response.data
               
                })

          },

          getProducts(){
            axios.get("/api/current/get_all_products")
            .then(response => {
                
                this.products = response.data
        
          })
        
        
        
        },

        getStorageTickets(){
          this.createTicketDto = JSON.parse(sessionStorage.getItem('cineverse-Tickets'));
        },

        addSelectedProductCombo(combo) {
            this.selectedProductCombos.push(combo);
   



            this.totalPrice = this.limitDecimals(this.totalPrice + combo.comboFinalPrice, 2);
   

         
          },
          addSelectedProduct(product) {
       
        

           
            let findProduct = this.selectedProducts.find(selectedProduct => selectedProduct.productId === product.id);
    
            if(findProduct){
              findProduct.productQuantity +=1;
            } else {
              this.selectedProducts.push({
                productId: product.id,
                productQuantity: 1,
                productName: product.name,
                productPrice: product.productPrice
             });
            }

      

        
         this.totalPrice = this.limitDecimals(this.totalPrice + product.productPrice, 2) ;
         
         
   
       
    },

    limitDecimals(number, maxDecimals) {
      const factor = 10 ** maxDecimals;
      return Math.round(number * factor) / factor;
    },

          removeSelectedProductCombo(combo) {
            this.selectedProductCombos = this.selectedProductCombos.filter((selectedCombo) => selectedCombo.id !== combo.id);

            this.totalPrice = this.limitDecimals(this.totalPrice - combo.comboFinalPrice, 2);

          },
          removeSelectedProduct(product) {
  
            this.selectedProducts = this.selectedProducts.filter((selectedProduct) => selectedProduct.productId !== product.productId);
            this.totalPrice = this.limitDecimals(this.totalPrice - product.productPrice, 2) ;

          },
          clearItems() {
            this.selectedProductCombos = [];
            this.selectedProducts = [];
            this.totalPrice = 0;
          },
         
    
          saveProducts(){
      

            sessionStorage.setItem("selectedCombos", JSON.stringify(this.selectedProductCombos));
            sessionStorage.setItem("selectedProducts", JSON.stringify(this.selectedProducts));

            this.buyTickets();
            


          },

         
          buyTickets(){
            axios.post('/api/current/create_ticket', this.createTicketDto)
            .then(response => {
              
              this.purchaseId = response.data;
              sessionStorage.setItem('purchaseId', this.purchaseId);
        
              this.buyProductCombos();
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
            
          },

          buyProducts(){
            axios.post("/api/current/purchase/add_purchase_item", this.selectedProducts, {
                params: {
                    purchaseId: this.purchaseId
                }
            }).then(response => window.location.href="./checkout.html");
          }



    },


    



}).mount('#app')
