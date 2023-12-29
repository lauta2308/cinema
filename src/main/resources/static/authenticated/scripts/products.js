

const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],

    data() {

        return {
   
  
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

     
        

    },



    

    computed: {

       
          
          



    },
    methods: {


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

       

        addSelectedProductCombo(combo) {
            this.selectedProductCombos.push(combo);
   



            this.totalPrice = this.limitDecimals(this.totalPrice + combo.price, 2);
   

         
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

            this.totalPrice = this.limitDecimals(this.totalPrice - combo.price, 2);

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

            window.location.href="./checkout.html"
            


          },

         
    


    },


    



}).mount('#app')
