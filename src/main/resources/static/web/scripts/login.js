const { createApp } = Vue

createApp({

    data() {

        return {
     
            formFirstName: "",
            formLastName: "",
            formEmail: "",
            formPassword: "",
            errorMessage: ""
           
        }
    },
    created() {

      


    },

    computed: {




    },
    methods: {

        showLoginForm(){

            this.$refs.registerForm.style.display = "none";
            this.$refs.loginForm.style.display = "block";

        },

        showRegisterForm(){
            this.$refs.loginForm.style.display = "none";
            this.$refs.registerForm.style.display = "block";
        },

        validateData(action){
            this.errorMessage = "";
        
            if(!this.validateEmail()){
                this.errorMessage = "The email should be example@domain.com"
                return false;
            }

            if(action === 'register'){
                
                if(!this.validateFirstName){
                    this.errorMessage = "First name should be at least 2 characters long";
                }

                if(!this.validateLastName){
                    this.errorMessage = "Last name should be at least 2 characters long"
                }

                if(!this.validatePassword()){
                    this.errorMessage = "The password should include at least 1 Uppercase, 1 LowerCase, 1 number, 1 symbol and be at least 8 characters"
                }
            }

            return true;


        },

        validateFirstName(){
            return this.formFirstName.length <= 2;

        },

        validateLastName(){

            return this.formLastName.length <= 2;

        },

        validateEmail(){
            const regex = /@/;
            return regex.test(this.formEmail)

        },

        validatePassword(){

            const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            return regex.test(this.formPassword);
          

          

        },

        resetFormValues(){
            this.formFirstName = "";
            this.formLastName  = "";
            this.formEmail = "";
            this.formPassword = "";

        },
      
        loginClient(){

            if(this.validateData('login')){
                axios.post('/api/login', `email=${this.formEmail}&password=${this.formPassword}`)
                .then(response => {
                    this.resetFormValues();
                    localStorage.setItem('cineverseLogin', true);
                    console.log(localStorage.getItem('cineverseLogin'));
                    window.location.href = "../index.html"
                    
        
                })
                .catch(error => {
                        console.log(error);
                    
                })
            }
          
          
        },

        registerClient(){

          

            if(this.validateData('register')){
                axios.post('/api/register_client', {
                    "name": this.formFirstName,
                    "lastName": this.formLastName,
                    "email": this.formEmail,
                    "password": this.formPassword,
                })  .then(response => {
                   
                    this.loginClient();
                    console.log(response);
                    
        
                })
                .catch(error => {
                        this.errorMessage = error.response.data;
                        console.log(error);
                    
                        
                })
            }

           
           
        },

        resetErrorMessage(){
            this.errorMessage = "";
        }




    



    },



}).mount('#app')


