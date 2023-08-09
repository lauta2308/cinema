const { createApp } = Vue
import menuBehavior from "../../web/scripts/menu.js";
import userLogged from '../../web/scripts/userLogged.js';



createApp({
  mixins: [menuBehavior, userLogged],

    data() {

        return {
     
          currentPassword: "",
          newPassword: "",
          currentEmail: "",
          newEmail: "",
          emailError: "",
          passwordError: "",
          
           

            
        }
    },
    created() {

    
     

       

        

    },



    

    computed: {




    },
    methods: {

          resetErrorMessage(){
            this.emailError = "",
            this.passwordError = "";
           
          },

          validEmail(){
            const regex = /@/;
            return regex.test(this.newEmail);

        },


        validatePassword(){

          if(this.currentPassword === ""){
            this.passwordError = "Required current password is empty";
          } else if(this.newPassword === ""){
            this.passwordError = "Required new password is empty"
          } else if(this.currentPassword === this.newPassword){
            this.passwordError = "Current and new password are same";
          } else if(!this.validPassword()){
            this.passwordError = "New Password should contain at least 1 UpperCase, 1 LowerCase, 1 number and 1 Symbol";
          } else {
              this.updatePassword();
          }
        },

        
        validPassword(){

          const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
          return regex.test(this.newPassword);
        
        

      },

      updatePassword(){

        let changePasswordDto = {
          currentPassword: this.currentPassword,
          newPassword: this.newPassword
        }

        axios.patch("/api/current/password", changePasswordDto)
        .then(response => {
          
          console.log(response.data)
        })
        .catch((Error) => this.passwordError = Error.response.data)
      },

      validateEmail(){
        if(this.currentEmail === ""){
          this.emailError = "Required current email is empty";
        } else if(this.newEmail === ""){
          this.emailError = "Required new email is empty"
        } else if(this.currentEmail === this.newEmail){
          this.emailError = "Current and new email are same";
        } else if(!this.validEmail()){
          this.emailError = "Email should be example@test.com";
        } else {
            this.updateEmail();
        }

      },

        updateEmail(){


          let changeEmailDto = {
            currentEmail: this.currentEmail,
            newEmail: this.newEmail
          }
  
          axios.patch("/api/current/email", changeEmailDto)
          .then(response => window.location.href = "../web/login.html")
          .catch((Error) => this.errorMessage = Error.response.data)

        }

     

    



    },


    



}).mount('#app')
