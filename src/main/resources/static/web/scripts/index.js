const { createApp } = Vue

createApp({

    data() {

        return {
     
            isMenuOpen: false,
            movies: [],
            slides: 7,
            clientRole: "",
            clientName: "",
            
        }
    },
    created() {

        this.loadMovies();

        if(this.checkLocalAuth){
            this.getUserLogged();
        }



    },

    computed: {




    },
    methods: {

        checkLocalAuth(){
            return localStorage.getItem('cineverseLogin')
        },

        getUserLogged(){
            axios.get("/api/authenticated_user")
            .then(response => {
                this.clientRole = response.data.clientRol,
                this.clientName = response.data.name
             } )
    
        },
      
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
          },

         
         loadMovies() {
         
            axios.get(`/api/get_on_schedule_movies`).then((response) => {
                this.movies = response.data,
                this.slides = this.movies.length;
               console.log(this.movies)
                
         })
            
        },



    



    },



}).mount('#app')


