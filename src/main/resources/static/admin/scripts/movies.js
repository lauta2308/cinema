const { createApp } = Vue;
import menuBehavior from '../../web/scripts/menu.js';


createApp({
    mixins: [menuBehavior],
      data() {
  
          return {
                
            movies: [],
            errorMessage: "",
            newMovieImage: "",
            newMovieTrailer: "",
            newMovieName: "",
            newMovieDescription: "",
            newMovieRestriction: "",
            newMovieDuration: "",
            newMovieLanguage: "",
            newMovieGenre: "",
            newMovieType: "",
            newMovieAvailability: "",
            enableEditMovie: false,
            movieEditId: "",


        
              
  
              
          }
      },
      created() {
  

        this.getMovies();
        
        
       
          
  
      },
  
  
  
      
  
      computed: {
     
  
         textAreaLimit(){
            return 500 - this.newMovieDescription.length 
         }
            
            
  
  
  
      },
      methods: {
  

        editMovie(movie){

            this.movieEditId = movie.id;

            this.enableEditMovie = true;

            this.newMovieImage = movie.movieImg;

            this.newMovieTrailer = movie.movieTrailer;

            this.newMovieName = movie.name;

            this.newMovieDescription = movie.description;

            this.newMovieRestriction = movie.movieRestriction;
     
            this.newMovieDuration = movie.duration;

            this.newMovieLanguage = movie.language;

            this.newMovieGenre = movie.movieGenre;

            this.newMovieType = movie.movieType;

            this.newMovieAvailability = movie.movieAvailability;

    


        },
        
        getMovies(){
            axios.get("/api/admin/get_movies")
            .then(response => {
                this.movies = response.data;
               
                this.movies.sort(function(a, b) {
                    return a.name.localeCompare(b.name);
                });



            })
            .catch(Error => this.errorMessage = Error.response.data );
        },

        reviewsLength(movie){

            return movie.reviews.length;


        },

        saveMovie(){

          
          
            if(this.newMovieImage === ""){
                this.errorMessage = "Add movie image"
            } else if(this.newMovieTrailer === ""){
                this.errorMessage = "Add movie trailer"
            } else if(this.newMovieName === ""){
                this.errorMessage = "Add movie name"
            } else if(this.newMovieDescription === ""){
                this.errorMessage = "Add movie description"
            } else if(this.newMovieRestriction === ""){
                this.errorMessage = "Add movie restriction"
            }else if(this.newMovieDuration === ""){
                this.errorMessage = "Add movie duration"
            }else if(this.newMovieLanguage === ""){
                this.errorMessage = "Add movie language"
            } else if(this.newMovieGenre === ""){
                this.errorMessage = "Add movie genre"
            }
             else if(this.newMovieType === ""){
                this.errorMessage = "Select movie type"
            }else if(this.newMovieAvailability === ""){
                this.errorMessage = "Select movie availability"
            }
            else {
                axios.post("/api/admin/add_movie", {
                    "movieImg": this.newMovieImage,

                    "movieTrailer": this.newMovieTrailer,

                    "name": this.newMovieName,

                    "description": this.newMovieDescription,

                    "movieRestriction": this.newMovieRestriction,

                    "duration": this.newMovieDuration,

                    "language": this.newMovieLanguage,

                    "movieGenre": this.newMovieGenre,


                    "movieType": this.newMovieType,

                    "movieAvailability": this.newMovieAvailability
                }).then(response => {
                    this.getMovies();
                    this.resetMovieForm()
    
                })
            }
        },

        saveEditedMovie(){

            if(this.newMovieImage === ""){
                this.errorMessage = "Add movie image"
            } else if(this.newMovieTrailer === ""){
                this.errorMessage = "Add movie trailer"
            } else if(this.newMovieName === ""){
                this.errorMessage = "Add movie name"
            } else if(this.newMovieDescription === ""){
                this.errorMessage = "Add movie description"
            } else if(this.newMovieRestriction === ""){
                this.errorMessage = "Add movie restriction"
            }else if(this.newMovieDuration === ""){
                this.errorMessage = "Add movie duration"
            }else if(this.newMovieLanguage === ""){
                this.errorMessage = "Add movie language"
            } else if(this.newMovieGenre === ""){
                this.errorMessage = "Add movie genre"
            }
             else if(this.newMovieType === ""){
                this.errorMessage = "Select movie type"
            }else if(this.newMovieAvailability === ""){
                this.errorMessage = "Select movie availability"
            } else {
                axios.patch(`/api/admin/edit_movie/${this.movieEditId}`, {
                    "movieImg": this.newMovieImage,

                    "movieTrailer": this.newMovieTrailer,

                    "name": this.newMovieName,

                    "description": this.newMovieDescription,

                    "movieRestriction": this.newMovieRestriction,

                    "duration": this.newMovieDuration,

                    "language": this.newMovieLanguage,

                    "movieGenre": this.newMovieGenre,


                    "movieType": this.newMovieType,

                    "movieAvailability": this.newMovieAvailability
                }).then(response => {
                    this.getMovies();
                    this.resetMovieForm();
                    this.enableEditMovie = false;
    
                })
            }
        },

        resetMovieForm(){
            this.newMovieImage= "",
            this.newMovieTrailer= "",
            this.newMovieName= "",
            this.newMovieDescription= "",
            this.newMovieRestriction= "",
            this.newMovieDuration= "",
            this.newMovieLanguage= "",
            this.newMovieGenre= "",
            this.newMovieType= "",
            this.newMovieAvailability= ""



        },


        resetErrorMessage(){
            this.errorMessage = "";
          },
  
      
  
  
  
      },
  
  
      
  
  
  
  }).mount('#app')
  