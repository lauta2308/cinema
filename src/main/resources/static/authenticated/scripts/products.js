

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
                    this.productCombos = response.data,
                    console.log(this.productCombos)
                })

          },

          getProducts(){
            axios.get("/api/current/get_all_products")
            .then(response => {
                
                this.products = response.data,
                console.log(this.products)
          })
        
        
        
        },

        getStorageTickets(){
          this.createTicketDto = JSON.parse(localStorage.getItem('cineverse-Tickets'));
        },

        addSelectedProductCombo(combo) {
            this.selectedProductCombos.push(combo);
            console.log(typeof(combo.comboFinalPrice));



            this.totalPrice = this.limitDecimals(this.totalPrice + combo.comboFinalPrice, 2);
   

         
          },
          addSelectedProduct(product) {
            console.log(product);
        

           
            let findProduct = this.selectedProducts.find(selectedProduct => selectedProduct.productId === product.id);
            console.log(findProduct);
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

          removeSelectedProductCombo(comboId) {
            this.selectedProductCombos = this.selectedProductCombos.filter((combo) => combo.id !== comboId);
          },
          removeSelectedProduct(productId) {
            console.log(productId);
            this.selectedProducts = this.selectedProducts.filter((product) => product.productId !== productId);
          },
          clearItems() {
            this.selectedProductCombos = [];
            this.selectedProducts = [];
            this.totalPrice = 0;
          },
         
    
          saveProducts(){
            console.log(this.selectedProductCombos);
            console.log(this.selectedProducts);

            localStorage.setItem("selectedCombos", JSON.stringify(this.selectedProductCombos));
            localStorage.setItem("selectedProducts", JSON.stringify(this.selectedProducts));

            this.buyTickets();
            


          },

         
          buyTickets(){
            axios.post('/api/current/create_ticket', this.createTicketDto)
            .then(response => {
              
              this.purchaseId = response.data;
              sessionStorage.setItem('purchaseId', this.purchaseId);
              console.log(response.data);
              console.log(this.purchaseId);
              this.buyProductCombos();
            })
        
         
            
              
             
          },

          buyProductCombos(){
            console.log(this.purchaseId);
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
