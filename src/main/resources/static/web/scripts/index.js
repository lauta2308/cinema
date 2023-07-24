const { createApp } = Vue

createApp({

    data() {

        return {
     
            isMenuOpen: false,
            movies: [],
            slides: 7,
            clientRole: "",
            clientName: "",
            carruselOffset: 0, // Offset para el carrusel
            carruselStep: 1,   // Cantidad de elementos que avanzará el carrusel al hacer click en el botón
            
        }
    },
    created() {

        this.loadMovies();

        if(this.checkLocalAuth()){
            console.log(this.checkLocalAuth());
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
                 .then(response => localStorage.setItem('cineverseLogin', false));
        
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

        moveCarrusel(step) {
            const carrusel = this.$refs.carrusel;
            const carruselWidth = carrusel.offsetWidth;
            const carruselItemWidth = carrusel.querySelector(".carrusel-item").offsetWidth;
            const carruselItems = carrusel.childElementCount;
            const totalWidth = carruselItemWidth * carruselItems + (2 * carruselItems - 1) * 2; // 2rem de separación entre elementos
            const maxOffset = totalWidth - carruselWidth;
          
            // Calcula el nuevo offset al mover el carrusel
            let newOffset = this.carruselOffset - step * carruselItemWidth;
          
            // Verifica si se ha llegado al final o al principio del carrusel
            if (newOffset < -maxOffset) {
              // Llegó al final, vuelve a comenzar desde el principio
              newOffset = 0;
            } else if (newOffset > 0) {
              // Está en el principio, no permite mover hacia la izquierda
              newOffset = 0;
            }
          
            this.carruselOffset = newOffset;
          },


    



    },


    



}).mount('#app')


