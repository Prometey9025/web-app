export interface BRankingProps {
  name: string,
  position: number,
  score: string | number,
}

export type FCBRanking = React.FC<BRankingProps>