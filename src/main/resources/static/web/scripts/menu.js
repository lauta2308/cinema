
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

  },
};

export default menuBehavior; // Exporta el objeto menuBehavior para poder importarlo en otros archivos