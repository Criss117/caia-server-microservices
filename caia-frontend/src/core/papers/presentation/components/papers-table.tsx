import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/core/shared/components/ui/table";
import { format } from "@formkit/tempo";
import { PaperDto } from "../../data/dto/papers.dto";
import { PAPER_STATE } from "../../../shared/lib/constants/paper-state";
import { Button } from "@/core/shared/components/ui/button";
import Link from "next/link";
import { ROUTES } from "@/core/shared/lib/constants/routes";

interface Props {
  papers: PaperDto[];
  inConference?: boolean;
}

const PapersTable = ({ papers, inConference }: Props) => {
  return (
    <Table>
      <TableCaption>Lista de papers</TableCaption>
      <TableHeader>
        <TableRow>
          <TableHead className="w-[200px]">Conferencia</TableHead>
          <TableHead className="">Nombre</TableHead>
          <TableHead className="">Estado</TableHead>
          <TableHead className="">Paper</TableHead>
          <TableHead className="text-right">Revisor(es)</TableHead>
          <TableHead className="text-right">Última actualización</TableHead>
          <TableHead className="text-right">Acciones</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {papers.map((paper) => (
          <TableRow key={paper.id}>
            <TableCell className="font-medium">
              {paper.conferenceEntity.name}
            </TableCell>
            <TableCell>{paper.title}</TableCell>
            <TableCell>{PAPER_STATE[paper.state]}</TableCell>
            <TableCell>
              <Button asChild variant="outline">
                <Link
                  href={
                    "http://localhost:8080/api/papers/files/" + paper.fileName
                  }
                  download={paper.fileName}
                  target="_blank"
                >
                  Descargar paper
                </Link>
              </Button>
            </TableCell>
            <TableCell className="text-right">
              Esperando asignación de revision
            </TableCell>
            <TableCell className="text-right">
              {format(new Date(paper.auditMetadata.updatedAt))}
            </TableCell>
            <TableCell className="text-right">
              {inConference ? (
                <Button asChild>
                  <Link
                    href={ROUTES.DASHBOARD.EDIT_PAPER.replace(
                      "[paperId]",
                      paper.id.toString()
                    )}
                  >
                    Asignar revisor
                  </Link>
                </Button>
              ) : (
                <Button asChild>
                  <Link
                    href={ROUTES.DASHBOARD.EDIT_PAPER.replace(
                      "[paperId]",
                      paper.id.toString()
                    )}
                  >
                    Editar
                  </Link>
                </Button>
              )}
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
};

export default PapersTable;
