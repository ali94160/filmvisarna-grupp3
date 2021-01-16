<template>
  <div class="container">
    <img class="loggan" :src="loggan" /><a class="logoName">Filmvisarna</a>
       <hr>
    <h4>Vilka är vi?</h4>

    <p>
      <span
        >Filmvisarna grundades Januari 2021 av en grupp studerandra från Teknik Högskolan i Lund.
      </span>
      <br>
      <span>
        Gruppens medlemmar: Alex, Ali, Anna, Isabella, Lukas, Oscar, Qiling.
      </span>
        <br>
      <span>Filmvisarna har som mål att leverera dem bästa och senaste filmerna i marknaden. Vi riktar vårat utbud mot alla åldrar.</span>
    </p>
      
<h4 class="salTitle">Salonger</h4>
 <div class="salongerna">

 <div class="storaImg">
<h5>Stora salongen</h5>
      <img src="https://i.postimg.cc/HW4GYq0Q/Ska-rmavbild-2021-01-15-kl.png" alt="" class="stora">

      <p class="bigInfo">Våran stora salong med upp till 81 sittplatser. Perfekta salongen för att titta på en premiärfilm med flertal åskådare samtidigt. Du sitter alltid bekvämt på alla våra fåtöljer som är justerbara efter din bekvämlighet. </p>
 </div>


     <div class="lillaImg">
       <h5 class="lilTitle">Lilla salongen</h5>
       <img src="https://i.postimg.cc/rpHdphWk/Ska-rmavbild-2021-01-16-kl-00-38-09.png" alt="">

       <p class="lilInfo">Våran lilla salong med upp till 56 sittplatser är den perfekta salongen för dig som vill uppleva en mer mysig känsla under besöket. Lika bekväma fåtöljer som den stora salongen men med en mysigare känsla.</p>
     </div>
     

 </div>

          

</template>

<script>
export default {
  data() {
    return {
      loggan: "https://i.postimg.cc/mrRHK1mz/logo-Nyaste-2-1.png",
    };
  },
};
</script>

<style scoped>
.lol {
  position: relative;
  margin: 0 auto;
}
.logoName {
  position: relative;
  bottom: 60px;
  font-size: 80px;
  color: white;
}

.loggan {
  margin: 50px 50px 10px 9vw;
  max-width: 200px;
}

.salTitle {
  margin-top: 6vw;
  margin-bottom: 2vw;
}

.salons {
  margin-bottom: 10%;
}

h1 {
  width: fit-content;
  margin: 2vh 0;
}

h5 {
  width: fit-content;
  margin: 20px auto;
  margin-bottom: 30px;
}

.lilTitle {
  width: fit-content;
  margin: 22px auto;
  margin-bottom: 28px;
}

.lillaImg img {
  box-shadow: 0px 0px 15px 1px rgb(61, 61, 61);
  max-width: 31vw;
}

.lilInfo {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  margin-top: 9.5vw;
  margin-bottom: 60px;
}

.bigInfo {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  margin-bottom: 60px;
}

.stora {
  box-shadow: 0px 0px 10px 1px rgb(61, 61, 61);
  max-width: 30vw;
}

.salongerna {
  max-width: 30%;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 120px;
}
.storaText {
  margin: 0;
}
</style>