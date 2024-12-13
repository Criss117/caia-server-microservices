import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/core/shared/components/ui/carousel";
import AuthorCard from "./author-card";

const AuthorsList = () => {
  const randomKey = Math.floor(Math.random() * 10);

  return (
    <Carousel>
      <CarouselContent>
        {Array.from({ length: randomKey }).map((_, index) => (
          <CarouselItem key={index} className={"md:basis-1/2 2xl:basis-1/4"}>
            <AuthorCard />
          </CarouselItem>
        ))}
      </CarouselContent>
      <CarouselPrevious />
      <CarouselNext />
    </Carousel>
  );
};

export default AuthorsList;
