<template>
  <div @click="goToDetails" class="container">
    <img :src="movie.imgUrl" alt="">
    <div>
      <h3>{{ movie.title }}</h3>
      <p><span class="title">Åldersgräns:</span> {{ movie.age }}</p>
      <p><span class="title">Språk:</span> {{ movie.languages[0] }}</p>
      <p><span class="title">Handling:</span> {{ movie.description }}</p> 
    </div>
  </div>
</template>

<script>
export default {
  props:['movie'],
  methods: {
    goToDetails(){
      this.$router.push('/moviedetails/' + this.movie.id)
    }
  }
}
</script>

<style scoped>
  .container{
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-gap: 2vw;
    margin: 0 auto;
    margin-top: 2vh;
    background-color: var(--lightgrey);
    border-radius: 10px;
    color: var(--darkgrey);
    cursor: pointer;
  }

  .container:hover {
    opacity: 70%;
  }

  .container div{
    padding-right: 1.5vw;
  }

  img{
    width: 100%;
    border-radius: 10px 0 0 10px;
  }

  h3{
    font-size: 2.8em;
  }

  p{
    font-size: 1em;
  }

  .title {
    font-weight: bold;
  }


</style>