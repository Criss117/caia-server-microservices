import Image from "next/image";
import { auth } from "@/auth";

import { IMAGES } from "@/core/shared/lib/constants/images";
import { format } from "@formkit/tempo";
import AuthorsList from "@/core/papers/presentation/components/authors-list";
import BackButton from "@/core/shared/components/ui/backbutton";
import ParticipateButton from "../components/participate-button";
import { cn } from "@/core/shared/lib/utils";
import ConferenceActions from "../components/conference-actions";
import findOneConferenceAction from "../../data/actions/find-one-conference";

interface Props {
  slug: string;
  onDashboard?: boolean;
}

const ConferenceScreen = async ({ onDashboard = false, slug }: Props) => {
  const session = onDashboard ? await auth() : null;

  const date = new Date();

  const { data } = await findOneConferenceAction({ slug, jwt: session?.jwt });

  if (!data || !data.conference) {
    return null;
  }

  return (
    <div className="w-[95%] xl:w-[60%] mx-auto">
      <div className="flex justify-between">
        <BackButton className="mb-10" />
        {data.isOrganizer && <ConferenceActions conference={data.conference} />}
      </div>
      <header className="space-y-5">
        <Image
          src={IMAGES.CONFENRENCE}
          alt="conference"
          width={1280}
          height={300}
          className="object-cover aspect-[16/4] w-full"
        />
        <div
          className={cn(
            "flex justify-between",
            !onDashboard ? "w-[70%] mx-auto" : ""
          )}
        >
          <div>
            <h2 className="text-4xl font-black">{data.conference.name}</h2>
            <p>{format(date, "long")}</p>
          </div>
          <div>{!data.isOrganizer && <ParticipateButton slug={slug} />}</div>
        </div>
      </header>
      <div
        className={cn("mt-10 space-y-5", !onDashboard ? "w-[70%] mx-auto" : "")}
      >
        <p className="text-lg">{data.conference.description}</p>
        <p className="text-lg">
          Lorem ipsum, dolor sit amet consectetur adipisicing elit. Corporis
          natus voluptate rerum porro voluptatem ratione necessitatibus, sequi
          veritatis officia consequatur velit quae molestias facere quisquam
          sit? Facere ut exercitationem non. Lorem ipsum, dolor sit amet
          consectetur adipisicing elit. Corporis natus voluptate rerum porro
          voluptatem ratione necessitatibus, sequi veritatis officia consequatur
          velit quae molestias facere quisquam sit? Facere ut exercitationem
          non.Lorem ipsum, dolor sit amet consectetur adipisicing elit. Corporis
          natus voluptate rerum porro voluptatem ratione necessitatibus, sequi
          veritatis officia consequatur velit quae molestias facere quisquam
          sit? Facere ut exercitationem non.
        </p>
      </div>
      <div
        className={cn("mt-10 space-y-5", !onDashboard ? "w-[70%] mx-auto" : "")}
      >
        <h3 className="font-bold text-2xl">Autores</h3>
        <AuthorsList />
      </div>
      <footer className="mt-10 h-52"></footer>
    </div>
  );
};

export default ConferenceScreen;
