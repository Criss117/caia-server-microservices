import { IMAGES } from "@/core/shared/lib/constants/images";
import Image from "next/image";

const HeroSection = () => {
  return (
    <section className="w-[90%] md:w-[80%] xl:w-[60%] mx-auto my-20">
      <header className="flex flex-col md:flex-row items-center gap-x-10 space-y-10 md:space-y-0">
        <h1 className="text-5xl font-bold text-center text-lightprimary-200">
          C.A.I.A.
          <span className="block text-black text-3xl">
            Crea, administra y difunde
          </span>
        </h1>
        <Image
          src={IMAGES.BANNER}
          alt="banner caia"
          width={720}
          height={400}
          className="object-cover aspect-video"
        />
      </header>
    </section>
  );
};

export default HeroSection;
