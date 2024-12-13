import Image from "next/image";
import { AUTHORS_IMAGES } from "../../../shared/lib/constants/images";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/core/shared/components/ui/card";

const AuthorCard = () => {
  const keys = Object.keys(AUTHORS_IMAGES);
  const randomKey = keys[
    Math.floor(Math.random() * keys.length)
  ] as keyof typeof AUTHORS_IMAGES;

  return (
    <Card>
      <CardContent>
        <CardHeader>
          <CardTitle></CardTitle>
          <CardDescription></CardDescription>
        </CardHeader>

        <Image
          src={AUTHORS_IMAGES[randomKey]}
          alt="author"
          className="rounded-md object-cover mx-auto"
          width={300}
          height={300}
        />
      </CardContent>

      <CardFooter className="flex flex-col justify-start items-start space-y-2">
        <p className="font-semibold">Nombre del autor</p>
        <p className="text-sm">Lorem ipsum dolor sit amet.</p>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat, illo?
        </p>
      </CardFooter>
    </Card>
  );
};

export default AuthorCard;
