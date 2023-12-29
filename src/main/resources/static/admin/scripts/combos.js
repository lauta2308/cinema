const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            combos: [],
            comboName: "",
            comboPrice: 0,
            allProducts: [],
            comboProducts: [],
            comboEdited: [],
            enableEditProductCombo: false,
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

        this.enableEditProductCombo = false;

        axios.get("/api/current/get_all_products")
        .then(response => {
            
     
            
            this.allProducts = response.data
     
        })
        

      },

      addProductToCombo(product){

        this.comboProducts.push(product);
        this.comboPrice = this.comboPrice + product.productPrice;
      },

      deleteProductFromCombo(product){

        this.comboPrice = this.comboPrice - product.productPrice;
        let productIndex = this.comboProducts.indexOf(product);
        
        this.comboProducts.splice(productIndex, 1);

        console.log(this.comboProducts);
         
        
      },

      editCombo(combo){

        this.enableEditProductCombo = true;
        this.comboProducts = combo.products;
        this.comboName = combo.name;
        this.comboPrice = combo.price;
        this.comboEdited = combo;

      },
      saveNewCombo(){


    
        axios.post("/api/admin/create_product_combo", {
          "comboName": this.comboName,
          "comboPrice": this.comboPrice.toFixed(2),
          "products": this.comboProducts
        })
        .then(response =>  window.location.reload())
          

      .catch(Error => {
        this.errorMessage = Error.message;
      })
      },

      saveEditedCombo(){

        axios.patch("/api/admin/edit_combo", {
          "id": this.comboEdited.id,
          "comboName": this.comboName,
          "comboPrice": this.comboPrice.toFixed(2),
          "products": this.comboEdited.products
        }).then(response =>  window.location.reload())
        .catch(Error => this.errorMessage = Error.response.data);
        


      },

  
          

      
      resetErrorMessage(){
        this.errorMessage = "";
      },

   

  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  