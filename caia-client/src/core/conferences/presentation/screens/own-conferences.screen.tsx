import { Button } from "@/core/shared/components/ui/button";
import { Input } from "@/core/shared/components/ui/input";
import ConferencesList from "../components/conferences-list";
import Link from "next/link";
import { ROUTES } from "@/core/shared/lib/constants/routes";

const OwnConferencesScreen = () => {
  return (
    <>
      <header>
        <h2 className="text-4xl font-black">Mis Conferencias</h2>
      </header>
      <div className="flex justify-between mt-10">
        <Input
          placeholder="Buscar Conferencias"
          type="text"
          className="w-1/3 border-lighttext-100"
        />
        <Button className="bg-lightprimary-200" asChild>
          <Link href={ROUTES.DASHBOARD.CREATE_CONFERENCES}>
            Crear Conferencia
          </Link>
        </Button>
      </div>
      <div>
        <ConferencesList />
      </div>
    </>
  );
};

export default OwnConferencesScreen;
