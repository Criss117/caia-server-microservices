import Image from "next/image";
import Link from "next/link";
import { IMAGES } from "../../../shared/lib/constants/images";
import { format } from "@formkit/tempo";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import { ConferencesDto } from "../../data/dto/conference.dto";

interface Props {
  conference: ConferencesDto;
  onDashboard?: boolean;
}

const ConferenceCard = ({ conference, onDashboard = false }: Props) => {
  const date = new Date();

  return (
    <Link
      href={
        onDashboard
          ? ROUTES.DASHBOARD.CONFERENCES + "/" + conference.slug
          : ROUTES.CONFERENCE + "/" + conference.slug
      }
      className="mx-auto hover:shadow-lg w-64 xl:w-72 rounded-lg transition-all duration-500"
    >
      <header>
        <Image
          src={IMAGES.CONFENRENCE}
          alt="conference"
          width={320}
          height={180}
          className="object-cover aspect-video rounded-lg"
        />
      </header>
      <div className="px-5 py-2 space-y-2 mb-5 mt-2">
        <h3 className="font-semibold text-xl">{conference.name}</h3>
        <p>{format(date, "long")}</p>
        <p className="font-semibold">Desde $ 10,000.0</p>
        <p>{conference.description}</p>
      </div>
    </Link>
  );
};

export default ConferenceCard;
