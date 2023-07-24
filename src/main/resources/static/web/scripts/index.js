const { createApp } = Vue

createApp({

    data() {

        return {
     
            isMenuOpen: false,
            movies: [],
            slides: 7
            
        }
    },
    created() {

        this.loadMovies();



    },

    computed: {




    },
    methods: {
      
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


