const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            products: [],
            newProductName: "",
            newProductPrice: "",
            newProductType: "",
            newProductContent: "",
            productError: ""

        
              
  
              
          }
      },
      created() {
  

        this.getProducts();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         
            
            
  
  
  
      },
      methods: {
  
    
      getProducts(){

        axios.get("/api/admin/get_all_products")
        .then(response => {
            
            
            this.products = response.data
            this.products.sort(function(a, b) {
                return a.name.localeCompare(b.name);
            });
            
        })

        

      },

      patchAvailable(product_id){
        console.log(product_id);
        axios.patch(`/api/admin/product_availability/${product_id}`

        ).then(response => this.getProducts());

      },

      saveProduct(){


        if(this.newProductName === ""){
            this.productError = "Insert Product Name"
        } else if(this.newProductPrice === ""){
            this.productError = "Insert Product Price"
        } else if(this.newProductType === ""){
            this.productError = "Insert Product Type"
        } else if(this.newProductContent === ""){
            this.productError = "Insert Product Net Content"
        } else {
            axios.post("/api/admin/add_product", {
                "productName": this.newProductName,
                "productPrice": this.newProductPrice,
                
                "productType": this.newProductType,
                "net_content": this.newProductContent
            }).then(response => {
                this.getProducts();
                this.resetProductForm()

            })
        }

      },

      resetProductError(){
        this.productError = "";
      },

      resetProductForm(){
        this.newProductName = "",
        this.newProductPrice = "",
        
        this.newProductType = "",
        this.newProductContent = ""
      }
  
  
      
  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  