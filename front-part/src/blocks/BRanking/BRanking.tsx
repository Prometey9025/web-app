import { Box } from "@mui/system"
import { BRankingItem } from ".";
import { useContext } from "react";
import { RatingContext } from "../../context/rating";

export const BRanking = ({view}: {view: 'courses' | 'competitions'}) => {
  const data = useContext(RatingContext);
  const firstName = localStorage.getItem('firstName')
  const lastName = localStorage.getItem('lastName')

  return (
    <>
      <Box component='h2'>Рейтинговая таблица</Box>
      <BRankingItem name={`${firstName} ${lastName}`} position={1} score={view === 'competitions' ? data.infoRating.competitionsRating : data.infoRating.coursesRating} />
    </>
  )
}
