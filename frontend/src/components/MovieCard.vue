<template>
  <div class="container">
    <img :src="imgUrl" alt="">
    <div>
      <h3>{{title}}</h3>
      <p>Datum {{date}}</p>
      <p>Platser kvar: </p>
      <p>Pris: </p>
      <p>Åldersgräns: </p>
      <p>Handling: {{description}}</p> 
    </div>
  </div>
</template>

<script>
export default {
  props:['title','date','description', 'imgUrl']
}
</script>

<style scoped>
  .container{
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-gap: 2vw;
    margin: 0 auto;
    margin-top: 2vh;
    border: 1px solid var(--lightgrey);
    border-radius: 10px;
  }

  img{
    width: 100%;
    border-radius: 10px;
  }

  h3{
    font-size: 2.8em;
  }

  p{
    font-size: 1em;
  }
</style>