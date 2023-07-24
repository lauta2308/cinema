const { createApp } = Vue

createApp({

    data() {

        return {
     
            formFirstName: "",
            formLastName: "",
            formEmail: "",
            formPassword: "",
            formBornDate: "",
            errorMessage: ""
           
        }
    },
    created() {

      


    },

    computed: {




    },
    methods: {
      
        loginClient(){

            
          
            axios.post('/api/login', `email=${this.formEmail}&password=${this.formPassword}`)
            .then(response => {
               
                console.log(response);
                
    
            })
            .catch(error => {
                    console.log(error);
                
            })
        },

        registerClient(){

            this.errorMessage = "";
            axios.post('/api/register_client', {
                "name": this.formFirstName,
                "lastName": this.formLastName,
                "email": this.formEmail,
                "password": this.formPassword,
                "bornDate": this.formBornDate
            })  .then(response => {
               
                console.log(response);
                
    
            })
            .catch(error => {
                    this.errorMessage = error.response.data;
                    console.log(error);
                
                    
            })
        },

        resetErrorMessage(){
            this.errorMessage = "";
        }




    



    },



}).mount('#app')


