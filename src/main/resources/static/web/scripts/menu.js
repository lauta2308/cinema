
const menuBehavior = {
  data() {
    return {
      isMenuOpen: false,
      showSubmenu: false,
    };
  },
  methods: {
    toggleMenu() {
        this.isMenuOpen = !this.isMenuOpen;
      },

      toggleSubmenu() {


        this.showSubmenu = !this.showSubmenu;
      },

      
      logout(){
          axios.post("/api/logout")
          .then(response => window.location.href="./index.html");
      },
      

  },
};

export default menuBehavior; // Exporta el objeto menuBehavior para poder importarlo en otros archivos