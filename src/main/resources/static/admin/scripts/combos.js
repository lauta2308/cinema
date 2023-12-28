const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            combos: [],
            comboName: "",
            comboPrice: "",
            allProducts: [],
            comboProducts: [],
            enableEditProductCombo: false,
            newComboPrice: 0,
            errorMessage: ""
         

              
  
              
          }
      },
      created() {
  

        this.getCombos();
        this.getProducts();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         
            
            
  
  
  
      },
      methods: {
  
    
      getCombos(){

        axios.get("/api/current/get_product_combos")
        .then(response => {
            
          console.log(response.data)
            
            this.combos = response.data
     
        })

        

      },

      getProducts(){

        axios.get("/api/current/get_all_products")
        .then(response => {
            
     
            
            this.allProducts = response.data
     
        })
        

      },

      addProductToCombo(product){

        this.comboProducts.push(product);
        this.newComboPrice = this.newComboPrice + product.productPrice;
      },

      deleteProductFromCombo(product){

        this.newComboPrice = this.newComboPrice - product.productPrice;
        let productIndex = this.comboProducts.indexOf(product);
        
        this.comboProducts.splice(productIndex, 1);

        console.log(this.comboProducts);
         
        
      },

      saveNewCombo(){

        console.log(this.comboName);
        console.log(this.comboPrice.toFixed(2));
        console.log(this.comboProducts);
    
        axios.post("/api/admin/create_product_combo", {
          "comboName": this.comboName,
          "comboPrice": this.newComboPrice.toFixed(2),
          "products": this.comboProducts
        })
        .then(response => this.getCombos())

      .catch(Error => {
        this.errorMessage = Error.message;
      })
      },

  
          

      
      resetErrorMessage(){
        this.errorMessage = "";
      },


  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  