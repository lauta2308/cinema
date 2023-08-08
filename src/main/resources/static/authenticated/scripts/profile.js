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
          errorMessage: "",
           

            
        }
    },
    created() {

    
     

       

        

    },



    

    computed: {




    },
    methods: {

          resetErrorMessage(){
            this.errorMessage = "";
          },

          validEmail(){
            const regex = /@/;
            return regex.test(this.newEmail);

        },


        validatePassword(){

          if(this.currentPassword === ""){
            this.errorMessage = "Required current password is empty";
          } else if(this.newPassword === ""){
            this.errorMessage = "Required new password is empty"
          } else if(this.currentPassword === this.newPassword){
            this.errorMessage = "Current and new password are same";
          } else if(!this.validPassword()){
            this.errorMessage = "New Password should contain at least 1 UpperCase, 1 LowerCase, 1 number and 1 Symbol";
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
        .then(response => window.location.reload(true))
        .catch((Error) => this.errorMessage = Error.response.data)
      },

      validateEmail(){
        if(this.currentEmail === ""){
          this.errorMessage = "Required current email is empty";
        } else if(this.newEmail === ""){
          this.errorMessage = "Required new email is empty"
        } else if(this.currentEmail === this.newEmail){
          this.errorMessage = "Current and new email are same";
        } else if(!this.validEmail()){
          this.errorMessage = "Email should be example@test.com";
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
