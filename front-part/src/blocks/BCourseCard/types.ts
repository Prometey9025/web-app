export interface BCourseCardProps {
  name: string,
  author: string,
  id: string | number,
  success?: boolean
}

export type BCourseCardFC = React.FC<BCourseCardProps>