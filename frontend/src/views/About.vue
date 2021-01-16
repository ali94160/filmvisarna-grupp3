<template>
  <div class="container">
    <img class="loggan" :src="loggan" /><a class="logoName">Filmvisarna</a>
    <h4>Vilka är vi?</h4>

    <p>
      <span
        >Filmvisarna grundades Januari 2021 av en grupp studerandra från Teknik Högskolan i Lund.
      </span>
      <br>
      <span>
        Gruppens medlemmar: Alex, Ali, Anna, Isabella, Lukas, Oscar, Qiling.
      </span>
    </p>
<h4>Salonger</h4>
 <div class="salongerna">

 <div class="storaImg">
<h5>Stora salongen</h5>
      <img src="https://i.postimg.cc/HW4GYq0Q/Ska-rmavbild-2021-01-15-kl.png" alt="" class="stora">
 </div>


     <div class="lillaImg">
       <h5 class="lilTitle">Lilla salongen</h5>
       <img src="https://i.postimg.cc/rpHdphWk/Ska-rmavbild-2021-01-16-kl-00-38-09.png" alt="">
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

.lilTitle{
    width: fit-content;
  margin: 22px auto;
  margin-bottom: 28px;
}

.lillaImg img {
  max-width: 31vw;
}

.stora {
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