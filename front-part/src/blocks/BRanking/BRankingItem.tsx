import EmojiEventsIcon from '@mui/icons-material/EmojiEvents';
import { Chip, Grid, Typography } from "@mui/material";
import { FCBRanking } from "./types";

const iconPositionGenerate = (position: number) => {
  switch(position) {
    case 1: 
      return (
        <EmojiEventsIcon sx={{
          color: 'gold'
        }}/>
      )

    case 2:
      return (
        <EmojiEventsIcon sx={{
          color: 'silver'
        }}/>
      )

    case 3:
      return (
        <EmojiEventsIcon sx={{
          color: '#cd7f32'
        }}/>
      )

    default:
      return (
        <Chip label={position} size='small'/>
      )
  }
}

export const BRankingItem: FCBRanking = ({name, position, score}) => {

  return (
    <Grid container sx={{height: '30px'}}>
      <Grid item xs={2}>
        {iconPositionGenerate(position)}
      </Grid>
      <Grid item xs={7}>
        <Typography>{name}</Typography>
      </Grid>
      <Grid item xs={3}>
        <Typography>{score}</Typography>
      </Grid>
    </Grid>
  )
}
